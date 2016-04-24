# Pre-Scoop
<b>Find your bay area Pre-School</b>

The app consists of 3 main avtivities.  It starts in Main, and is the search page, to enter in different options to find schools. This also holds a button in the menu to go right to the list of favorite schools.

The ListActivity pulls the results from the database, where each item click brings the user to the DetailActivity to see more information about the school. The menu option for the favorites, will find all those schools from the database and refresh the list view with the results. It will toggle back to the previous search results when pressed again.

The details page shows a couple of photos of the school, its details and an option to select that school as a favorite.


<b>Bugs/Concerns</b><br>
I found a bug yesterday about a half hour before this was due.  It looked like i needed a null check on the object holding the search criteria when refreshing the list after a back press from the details page or toggling the favorites list.

The details page is not showing all of the additional photos for the school in the scroll view.  It might be an xml issue, or the pictures aren't being pulled out of the object correctly.

There isn't any real error control on the MainActivity, and the search in the database doesn't include the school Name in any of the permutations (only by itself).  so the user can enter in the name plus the other fields but the search won't actually include the name in it.

I also feel like i might have a cursor left open somewhere, and should put an override onStop in just to be sure there's nothing left open.

<br><br>


Paper ProtoType<br>
![alt tag](https://cloud.githubusercontent.com/assets/16617555/14061711/158f7828-f344-11e5-8d85-b8af9d5de494.jpg)

<br><br>

MainActivity<br>
![alt tag](https://cloud.githubusercontent.com/assets/16617555/14061689/96029f86-f343-11e5-80de-4fa331c4452f.jpg)

<br><br>
ListActivity<br>
![alt tag](https://cloud.githubusercontent.com/assets/16617555/14061688/96025972-f343-11e5-912a-f2d0e60bfd86.jpg)

<br><br>

DetailActivity<br>
![alt tag](https://cloud.githubusercontent.com/assets/16617555/14061687/95efce88-f343-11e5-926f-9e4d5b48e7e7.jpg)



<br><br><br><br><br><br><br><br>

<img src="https://github.com/salminnella/Pre-Scoop/blob/master/logcat2.jpg" /img>


<br><br><br><br><br><br><br><br>


<img src="https://raw.githubusercontent.com/salminnella/Pre-Scoop/master/logcat2.jpg" /img>