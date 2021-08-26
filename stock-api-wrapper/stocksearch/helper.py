import pandas as pd

data = pd.read_csv("stocks.csv").iloc[:, :2]
data.columns = [c.lower() for c in data.columns]
