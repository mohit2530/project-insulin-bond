
from pathlib import Path
import random

'''
Creating test sql for testing purposes only.
'''


def mainRun():
    initialStartUp();
    firstNames, lastNames, streetAddress = [], [], [];
    readingResources("firstNames", firstNames);
    readingResources("lastNames", lastNames);
    readingResources("streetAddress", streetAddress);

    createNewFile(firstNames, lastNames, streetAddress);



def createNewFile(firstNames, lastNames, streetAddress):
    '''
    Function create new file creates three arrays for now. WE CAN ADD MORE IF REQUIRED. Values are randomly generated. Since streetAddress is never allowed to be empty we decided to go with it for now. Can change later if required.
    '''
    file = open("jsonObject", "a");
    front, end = " { " , " }";
    file.write(front);
    for index, elements in enumerate(streetAddress):
        line = " firstName : " + firstNames[random.randrange(1, len(streetAddress))] + " lastName : " + lastNames[random.randrange(1, len(streetAddress))] + " street_address : " + streetAddress[random.randrange(1, len(streetAddress))] + ",";
        file.write(line);

    file.write(end);
    file.close();


def readingResources(fileName, arrayToStore):
    '''
    This function is used to take values from the resources and apply then to our tables to generate values.
    We must pass in the fileName and the array to store our values in as well.
    '''
    dataFolder = Path(r"resource/")
    fileToOpen = dataFolder / (fileName + ".txt")
    f = open(fileToOpen)
    for values in f:
        arrayToStore.append(values.split("\n")[0])
    return arrayToStore;


def initialStartUp():
    print(" ")
    print(" Welcome to Fake Data Generator ")
    print(" ===============================")



# APPLICATION RUNS HERE

mainRun()