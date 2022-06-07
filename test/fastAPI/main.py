#from http.client import HTTPException
import re
import json
from typing import List 
from uuid import uuid4, UUID
from fastapi import FastAPI, HTTPException

from models import User, Pref, Role, UserUpdateRequest

app = FastAPI() # creating an instance


## put database here
db: List[User] = [

    User(
        id=UUID("5a633721-a253-440c-bf3b-9e34339342f3"), #putting UUID('hsjakkaka') gives a permanent UUID
        first_name="akash",
        last_name="chakraborty",
        middle_name=" ",
        email="akash.chakraborty@irdeto.com",
        pref=Pref.veg,
        roles=[Role.user]
        ),

    User(
        id=UUID("5a733721-a253-440c-bf3b-9e34339342f5"),
        first_name="Oishmita",
        last_name="chakraborty",
        middle_name=" ",
        email="Oishmita.chakraborty@irdeto.com",
        pref=Pref.nonveg,
        roles=[Role.admin,Role.user,Role.manager,Role.caterer]

    ),
    User(
        id=UUID("5a633721-a253-440c-bf3b-9e34339342f5"), #putting UUID('hsjakkaka') gives a permanent UUID
        first_name="Priyanka",
        last_name="Arora",
        middle_name="",
        email="Priyanka.Arora@irdeto.com",
        pref=Pref.veg,
        roles=[Role.user,Role.admin,Role.manager]
        ),
    User(
        id=uuid4(),
        first_name="Rinku",
        last_name="Ganguly",
        middle_name=" ",
        email="r.aswani@irdeto.com",
        pref=Pref.veg,
        roles=[Role.user]
        )       

]

# @app.get("/")
# def root():
#     return {"message":"Hello Akash"}

### Aysnc waits for some other asynchronous fucntion to get into play then it goes on to return
# @app.get("/")
# async def root():
#     await foo()
#     return {"message": "Hello World"}

@app.get("/")
async def root():
    return {"message":"Hello Akash"}


@app.get("/api/v1/users")
async def fetch_users():
    return db;

#creating a new user
@app.post("/api/v1/users")
async def register_user(user: User):
    db.append(user)
    return {"id": user.id}


@app.delete("/api/v1/users/{user_id}")
async def delete_user(user_id:UUID):
    for user in db:
        if user.id == user_id:
            db.remove(user)
            return 

    raise HTTPException(
        status_code=404,
        detail="User with ID: {} does not exists".format(user_id)
    )

@app.put("/api/v1/users/{user_id}")
async def update_user(user_update: UserUpdateRequest, user_id: UUID):
    for user in db:
        if user.id == user_id:
            if user_update.first_name is not None:
                user.first_name = user_update.first_name
            if user_update.last_name is not None:
                user.last_name = user_update.last_name
            if user_update.middle_name is not None:
                user.middle_name = user_update.middle_name
            if user_update.roles is not None:
                user.roles = user_update.roles
            return
    raise HTTPException(
        status_code=404,
        detail="User with ID: {} does not exists".format(user_id)
    )