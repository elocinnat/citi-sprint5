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

@app.get("/arkk")
def arkk():
    with open("arkk.json") as f:
        return json.loads(f.read())