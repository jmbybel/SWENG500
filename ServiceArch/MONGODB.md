
Notes for Mongo here.



For the initial setup, Mongo will be running on the default port of 27017
start the mongod task or exe with a port param if you want a different port. At that point we'll need to add in some config file to sync both things.


Opted to keep MongoJack in because otherwise you had to deal with manually registering objects with Mongo. adding another library was worth it  rather than dealing with that for every object.
