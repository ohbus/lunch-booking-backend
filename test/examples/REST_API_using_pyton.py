
# Examples on making REST API calls with python

import requests
import json

def using_GET():
    print("UISNG GET")
    api_url = "https://jsonplaceholder.typicode.com/todos/1"
    response = requests.get(api_url)
    print(response.json()) # GET the json data
    print(response.status_code) #gets the status code


# USING POST
def using_POST():
    print("UISNG POST")
    api_url = "https://jsonplaceholder.typicode.com/todos"
    json_data = {
        "userId":1,
        'id': 201,
        "title":"Buy popcorn-akash ",
        "completed":False
    }
    headers =  {"Content-Type":"application/json"} # tells that the data type is json
    response = requests.post(api_url, data=json.dumps(json_data),headers=headers) # includes the headers along with it
    print(response.json())
    print(response.status_code)

# USING PUT

def using_PUT():
    print("UISNG PUT")
    api_url = "https://jsonplaceholder.typicode.com/todos/10"
    response = requests.get(api_url)
    print(response.json())
    json_data = {"userId": 1, "title": "Wash car", "completed": False}
    response = requests.put(api_url, json=json_data)
    print(response.json())
    print(response.status_code) # successful put requests will always return 200 as status code instead of 201


# USING PATCH
def using_PATCH():
    print("UISNG PATCH")
    api_url = "https://jsonplaceholder.typicode.com/todos/10"
    json_data = {"title": "Mow lawn"}
    response = requests.patch(api_url, json=json_data)
    print(response.json())
    print(response.status_code)


#USING DELETE

def using_DELETE():
    print("Using DELETE")
    api_url = "https://jsonplaceholder.typicode.com/todos/10"
    response = requests.delete(api_url)
    print(response.json())
    print(response.status_code)


#### Calling the functions here

using_POST()
using_GET()
using_PUT()
using_PATCH()
using_DELETE()

