from app import app
from stocksearch.helper import *

@app.get('/stock/search')
def search():
    out = []
    for row in data.values:
        out.append({
            "symbol": row[0],
            "name": row[1]
        })
    return out

