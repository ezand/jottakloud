# jottakloud

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Build Status](https://travis-ci.org/ezand/jottakloud.svg?branch=master)](https://travis-ci.org/ezand/jottakloud)
[![Dependency Status](https://www.versioneye.com/user/projects/58a607b24f72e00012ab9134/badge.svg?style=flat-square)](https://www.versioneye.com/user/projects/58a607b24f72e00012ab9134)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.ezand.jottakloud/jottakloud/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.ezand.jottakloud/jottakloud)

__A kotlin library for use with [Jottacloud](https://www.jottacloud.com)__

## Features:
* Get user-, device and mountpoint info
* List Jottacloud-content (files and folders)
* Download files

## Usage
```kotlin
val jottakloud = Jottakloud(JottacloudAuthentication("user", "pass"))
val user = jottakloud.getUser()
val device = jottakloud.getDevice("Jotta")
val mountPoint = jottakloud.getMountPoint("Jotta", "Photos")
val folder = jottakloud.getFolder("Jotta", "Photos", "2017/02/22")
val file = jottakloud.getFile("Jotta", "Photos", "2017/02/22/1481270269066.jpg")
val files = jottakloud.getFiles("Jotta", "Photos", "2017/02/22", recursive = true)
val fileAsStream = jottakloud.downloadFile("Jotta", "Photos", "2017/02/22/1481270269066.jpg")
```
