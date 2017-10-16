import datetime as dt
import pandas as pds
import matplotlib.pyplot as mp
import pandas_datareader.data as web
from matplotlib import style
from matplotlib.finance import candlestick_ohlc
import matplotlib.dates as mdates
import bs4 as bs  # called beautiful soup an HTML parsing library
import pickle  # for ease of saving list of companies
import requests

style.use('ggplot')

# start = dt.datetime(2017, 1, 1)
# end = dt.datetime(2017, 10, 9)

#df = web.DataReader('MSFT', 'yahoo', start, end)

#df.to_csv("MSFT.csv")
df = pds.read_csv("MSFT.csv", parse_dates = True, index_col = 0)
df["100ma"] = df["Adj Close"].rolling(window = 100, min_periods = 0).mean()

"""
resampling will take huge amount of data into a smaller amount that we actually care about
will make a new data frame .resample() is the call with interval as arg .mean() is average .rolling()
will make a moving average
"""

df_ohlc = df['Adj Close'].resample('10D').ohlc()
df_volume = df['Volume'].resample('10D').sum()

df_ohlc.reset_index(inplace = True) # allows for the date col to be an actual col

df_ohlc['Date'] = df_ohlc['Date'].map(mdates.date2num)  # changes the date to a number in matplotlib

ax1 = mp.subplot2grid((6, 1), (0, 0), rowspan = 5, colspan = 1)
ax2 = mp.subplot2grid((6, 1), (5, 0), rowspan = 5, colspan = 1, sharex = ax1)
ax1.xaxis_date() # takes the mdates2num and makes them as dates in graph

candlestick_ohlc(ax1, df_ohlc.values, width = 3, colorup = 'g') # takes axis, data, and width for thickness,
# colorup=color,
ax2.fill_between(df_volume.index.map(mdates.date2num), df_volume.values, 0) # fill from x where x is m dates to y
# where is values so 0 to y

# ax1.plot(df.index, df['Adj Close'], 'tab:blue')
ax1.plot(df.index, df['100ma'], 'tab:cyan')
# ax2.plot(df.index, df['Volume'], 'tab:cyan')

mp.show()
# print(df.tail(10))
