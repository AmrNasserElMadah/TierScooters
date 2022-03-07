# Project Description
It is very simple feature for getting location information for scooters  from **RestAPI** and show this info in Map and show the clicked scooter marker in bottom sheet.

## Setup
First you need to configure add your **google_maps_key** in your google_maps_api.xml in your values folder and also add your SHA1, the current one is public so you can use it.

## Used Design Pattern :
* MVVM (Model View ViewModel) - Clean code architecture with use case layer

## Used Language :
* Kotlin

## Used Technologies :
* Jetpack compose
* Coroutines with flow
* Dagger Hilt
* Android Architecture Component
* MVVM with Clean Code
* Git Flow
* Leak Canary
* Firebase Analytics
* Flavor for each main branch (Development - Staging - Production) - with relating to each app on firebase with different app id
* Chucker
* Unit test

## Overview
project code architecture follow clean architecture inspired by onion architecture by Uncle bob .

![Clean Architecture](https://koenig-media.raywenderlich.com/uploads/2019/06/Clean-Architecture-graph.png)