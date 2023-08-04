Application Name: C196 Term Tracker

Created By: Brandon Davis (010008848)

Purpose of Application: The purpose of this application is to create an Android application that
will track a students term information including Term, Courses, and Assessments.

Comments about Application: The application was fun to create, the other required documents will be
in a separate file for the submission. App was developed using Android 13 API 33, but tested using a
Pixel 3a using API 32.

Quick Notes: ( I would recommend testing this in anything below API 33. Originally this was tested in API 32
using a Pixel 3a for the emulator. Also please be sure to use the correct format for the date. The correct format
is MM-dd-yyyy. If you do not use this date format the notifications will not go off for the courses or assessments.
When you add or edit data from the Term home, Course home, or Assessment Home please be sure to refresh the recyclerview.
If you do not refresh the recyclerview you will not see the updated information in the view. I personally tested the notification
function as of 7/18/2023 4:29 pm CST to ensure it works. If you follow the recommendations and instructions then it should work out fine)


Instructions: First click the let's begin button, this will direct you to the Term Home screen. You
have the option click the + to go to the Add Term screen or click to go to the Course Home button.
If you click the + and go to the Add Term screen, add all the required information for your term.
Once that is finished click the save button, it will redirect you to the Term Home screen again. You'll
notice that it hasn't populated the recyclerview, you will need to click the menu in the top right corner
and click the refresh option. IF YOU DO NOT CLICK REFRESH THE NEW ITEMS WILL NOT BE SHOWN IN THE RECYCLERVIEW
From there you can click on the term to view the information and either change it or delete it from the database.
If you edit any data for a term in the view, you need to refresh the recyclerview so it'll have the up to date information
If you click the course home it will direct you to the course home where you will have the options of go to Assessment Home
or click the + to go to the Add Course screen. If you click the + to go to the Add Course screen, you will need to fill in all the
information for your course and click the save button. Once the save button has been clicked it will redirect you to the course home.
Like the Term Home the new course information will not be seen in the recyclerview you will need to click the menu in the top right
and select the Refresh option to refresh the view. IF YOU DO NOT CLICK REFRESH THE NEW ITEMS WILL NOT BE SHOWN IN THE RECYCLERVIEW
From there you can click the information in the recyclerview to edit, delete, share the note, or get notified of the class starting or ending.
If you edit any information for a course in the view, you need to refresh the recyclerview so it'll have the up to date information.
(NOTE : The notify option is dependent on the date that is in the start and end, depending on which is the current date will broadcast a message
stating either the course has started or ended)
If you click the go to assessment home button it will redirect you to the Assessment home where you will have 2 options.
The + button to go to the Add Assessment screen or the Go to main screen button. If you click the + to go to the Add Assessment screen
you will need to fill in all the information about your assessment and then click save. Once the save button has been clicked you will be
redirected back to the Assessment Home screen. Like the Term Home and Course Home the recently added items will not auto populate the view,
you will need to click the menu in the top right corner and select Refresh to refresh the view. IF YOU DO NOT CLICK REFRESH THE NEW ITEMS WILL NOT
BE SHOWN IN THE RECYCLERVIEW. From there you can select an assessment from the view and either edit it, delete it, or get notifications about the assessment
either when it starts or ends.
If you edit any information for an assessment in the view, you need to refresh the recyclerview so it'll have all the new information.
(NOTE: The notify options is dependent on the start and end date, just like the course notify option. )
Lastly you're left with the Go to Mainscreen button, when that is clicked it will redirect you back to the main activity.