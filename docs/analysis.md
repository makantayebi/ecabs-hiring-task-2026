# Analysis

This file explains the line of thought behind the development.

Reading the functional requirements, potential data classes for the implementation are:

`Rider`, `Driver`, `Ride`, possibly a `Location` class. I always keep an eye for further revelations.

I'll pick the maven build tool because I'm comfortable with that.

Initially i'll not enable Quarkus, but if time is ample I'll experiment with that as well.

A possibility of using redis to retain live data in the event of crashing, deployments, powercuts can be considered.

I'll need a backlog file to keep track of all the tasks. A mini-jira.

The core of the problem: Data structures for providing the services:

- req 1.a. We keep a list of available drivers then.
- req 2.b. We understand that spatial search is of interest.
- req 2.c. This is the second endpoint that makes changes to the driver list/set/collection/... So the driver list should be sync.
- req 3.a. This is telling me that I should keep a list of Rides too. If the completion of the ride is legit, we also update the driver list here again.

After searches, i see my options are

1. synced blocks of HashMap for Driver and Rides. For any operations with lookups this is highly more efficient than Lists. I expect more lookups than otherwise.
2. ConcurrentHashMap is also provided by java. it improves granularity of blocking the data. so it's more efficient. But in any case I have to sync the methods that modify this because THERE ARE LOOKUPS, and SEARCHES in the driver list. Searching in the map then marking a single driver as available, or updating the location etc. So the whole function should be sync. This means that if we're careful, the HashMap would also be enough. But I'd like to demonstrate my ability to understand the situation, if noone reads this document.
3. I can implement thread-safe KTrees for spatial searches of complexity O(log n), but this is not appropriate for the time given for the task, I'll throw it in the backlog.
