# Historian
This library captures the state of an object, so that at the end, the object's history is available.  

This thing will be divided into two parts:

## The Core
The core does the actual recording stuff. The recorded data will be made available to the parser, through
some kind of format (maybe XML?). Hasn't been decided yet.

## The Parser
This thing is a layer that sits on top of the core. All it does is present the data the core has recorded
in some easy to read form for the user.