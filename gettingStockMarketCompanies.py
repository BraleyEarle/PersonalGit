import bs4 as bs
import pickle
import requests
import datetime as dt
import os  # creates new directories
import pandas as pd
import pandas_datareader.data as web
import matplotlib.pyplot as plt
from matplotlib import style
import numpy as np

style.use('ggplot')

"""
pickle allows us to save the list of companies versus always going to a website for info
requests are for requests i think
beautiful soup turns the web source code into a Beautiful soup object which we can
manipulate as a Python object 
"""


def saveSAndP500Ticks():
    req = requests.get('https://en.wikipedia.org/wiki/List_of_S%26P_500_companies')
    # gets source code from wikipedia 
    soup = bs.BeautifulSoup(req.text, 'lxml')  # table data from the source code
    table = soup.find('table', {'class': 'wikitable sortable'})  # wiki table from wiki source code

    tickers = []
    for row in table.findAll('tr')[1:]:  # for each row after the row(1:) which is the table header
        ticker = row.findAll('td')[0].text  # ticker is the table data where we want the
        # .text end of it 
        tickers.append(ticker)  # then append it to the list tickers[]

    with open("sp500tickers.pickle", "wb") as f:  # "wb" is write bytes
        pickle.dump(tickers, f)

    return tickers


def getDataFromGoogle(reload_sp500=False):
    if reload_sp500:
        tickers = saveSAndP500Ticks()
    else:
        with open("sp500tickers.pickle", "rb") as f:
            tickers = pickle.load(f)
    if not os.path.exists('stocks_data'):
        os.makedirs('stocks_data')

    start = dt.datetime(2017, 1, 1)
    end = dt.datetime(2017, 10, 12)  # attempting to get updating data?

    for ticker in tickers[:290]:  # error reading lockheed martin data; google finance works but yahoo does not
        print(ticker)
        if not os.path.exists('stocks_data/{}.csv'.format(ticker)):
            df = web.DataReader(ticker, "google", start, end)
            df.to_csv('stocks_data/{}.csv'.format(ticker))
        else:
            print('Already have {}'.format(ticker))


def compile_data():
    with open("sp500tickers.pickle", "rb") as f:
        tickers = pickle.load(f)

    main_df = pd.DataFrame()

    for count, ticker in enumerate(tickers):  # iterate would be fine dont need enumerate
        df = pd.read_csv('stocks_data/{}.csv'.format(ticker))
        df.set_index('Date', inplace=True)
        df.rename(columns={'Adj Close': ticker}, inplace=True)
        df.drop(['Open', 'High', 'Low', 'Close', 'Volume'], 1, inplace=True)

        if main_df.empty:
            main_df = df
        else:
            main_df = main_df.join(df, how='outer')

        if count % 10 == 0:
            print(count)

    print(main_df.head())
    main_df.to_csv('sp500_joined_closes.csv')


def dataVisulaized():
    df = pd.read_csv('sp500_joined_closes.csv')
    df_corr = df.corr()  # generates the correlation values
    df_corr.to_csv('sp500_corr.csv')

    dataOne = df_corr.values
    fig = plt.figure()
    ax1 = fig.add_subplot(1, 1, 1)

    heatmapOne = ax1.pcolor(dataOne, cmap=plt.cm.RdYlGn)
    fig.colorbar(heatmapOne)

    ax1.set_xticks(np.arange(dataOne.shape[0] + 0.5), minor=False)
    ax1.set_yticks(np.arange(dataOne.shape[1] + 0.5), minor=False)
    ax1.invert_yaxis()
    ax1.xaxis.tick_top()

    column_labels = df_corr.columns
    row_labels = df_corr.index
    ax1.set_xticklabels(column_labels)
    ax1.set_yticklabels(row_labels)
    plt.xticks(rotation=90)
    heatmapOne.set_clim(-1, 1)
    plt.tight_layout()
    plt.show()


def main():
    #dataVisulaized()
    compile_data()
    # getDataFromGoogle()
    # saveSAndP500Ticks() # already have the tickers saved


main()
