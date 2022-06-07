# using pydantic -- a python library for data validation where we can declare 
# shape of our data as classes with attributes

import imp
from typing import List, Optional
from uuid import UUID,uuid4
from pydantic import BaseModel
from enum import Enum


class Pref(str,Enum):
    veg = 1
    nonveg = 0

class Role(str,Enum):
    admin = "admin"
    user = "user"
    caterer = "caterer"
    manager = "manager"
    

class User(BaseModel):
    id:Optional[UUID] = uuid4()
    #id: str = uuid4()
    first_name: str
    last_name: str
    middle_name: Optional[str]
    email: str
    pref: Pref
    roles:List[Role]

class UserUpdateRequest(BaseModel):
    first_name: Optional[str]
    last_name: Optional[str]
    middle_name: Optional[str]
    roles: Optional[List[Role]]



