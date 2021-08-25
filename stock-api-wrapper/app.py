import numpy as np
import pandas as pd

from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware

import json

app = FastAPI()
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"]
)

@app.get("/")
def home():
    return {
        "message": "hello from stock-api-wrapper"
    }

@app.get("/snp500")
def arkk():
    data = pd.read_csv("snp500.csv", sep="\t")
    data = data.iloc[:365,:]

    data.columns = [c.lower() for c in data.columns]
    for col in ["open", "close", "adj close", "high", "low"]:
        data[col] = [float(n.replace(",","")) for n in data[col]]
        data[col] = data[col].astype(np.float64)

    data["date"] = pd.to_datetime(data["date"])

    return {
        "data": {
            "date": list(data["date"]),
            "open": list(data["open"]),
            "close": list(data["close"]),
            "high": list(data["high"]),
            "low": list(data["low"])
        },

        "meta": {
            "symbol": "S&P 500",
            "name": "Standard & Poor's 500 Index",
            "currency": "USD"
        }
    }