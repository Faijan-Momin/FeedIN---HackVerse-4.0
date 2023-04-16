# Project Title

FeedIN - An initiative to diminish hunger in India



# Description

The primary goal of our Android application is to alleviate hunger among India's impoverished and underprivileged population. Our platform achieves this by repurposing excess food from various sources, including organizations, hotels, lodges, events, households, and more. Instead of wasting this food, we transform it into edible meals for those in need.

Our platform caters to three different types of users: donors, NGOs, and volunteers. Donors are any individuals, organizations or group who possess excess food that they would like to donate instead of discarding or throwing it off. The process involves posting details such as the type and amount of food, pictures of food, best before dates, etc. Interested NGOs or volunteers within a 10km radius can view the post or card and send a request for the food. The donor approves the request, and their location is shared via a map fragment and OSM API to the receiver, who can collect the food directly from the donor.

Our unique platform eliminates the need for intermediaries, and there is no monetary transaction involved, making it a safe and trustworthy process. The food moves directly from the donor to the receiver.

We have also incorporated gamification features into our platform to encourage increased donations. In Each donation both donor and receiver earns them 10 flames, which can later be redeemed for availing exciting prizes.

In the future, we plan to implement a weekly leaderboard, which will show the donors who have donated the most in that specific week. Also we'll be employing data analysis and visualization to leverage the data available on Firebase to improve donation rates. 
## Tech Stack

**Frontend:** XML

**Backend:** Java

**Database:** Firebase 

**API:** Open Street Maps (OSM) 

## Installation

Install FeedIN

```bash
    FeedIN.apk file is there which you can directly install in the android device.
```
Else 

```bash
    git clone https://github.com/Faijan-Momin/FeedIN---HackVerse-4.0.git
```
```bash
    cd FeedIN - HackVerse
```
```bash
    open project with Android Studio
```
```bash
    Select the debugging device . Note : Make sure your usb debugging is on
```
```bash
    Run the Code
```


## Libraries & Dependancies

```bash
    implementation 'com.google.firebase:firebase-auth:21.0.4'

    implementation 'com.google.firebase:firebase-firestore:24.1.2'

    implementation 'com.google.firebase:firebase-storage:20.0.1'

    implementation 'com.google.firebase:firebase-database:20.0.5'

    implementation 'com.google.firebase:firebase-messaging:23.1.1'

    implementation 'com.android.volley:volley:1.1.1'
    
    implementation 'org.osmdroid:osmdroid-android:6.1.8'

    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
```
