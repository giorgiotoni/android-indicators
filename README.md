Android Indicators
==================

Android library for indicators.


![alt tag](https://raw.githubusercontent.com/Tengio/android-indicators/master/config/images/indicators.jpg)


Version
-------

//TODO
[ ![Download](https://api.bintray.com/packages/tengioltd/maven/indicators/images/download.svg) ](https://bintray.com/tengioltd/maven/indicators/_latestVersion)


HOW TO
======

Dependencies
------------

```
dependencies {
    ...
    compile('com.tengio.android:indicators:latest_version')
    ...
}
```

Layouts
-------
Add ChipsView to the layout, like this:

```
<com.tengio.android.indicators. .... />
```


Library updates
---------------

We use bintray to deploy changes to jcenter. To deploy a new version make sure to define BINTRAY_USER and BINTRAY_KEY variables. Then run:

```
gradle bintrayUpload
```
