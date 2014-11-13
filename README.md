Zeitgeist 2014 - The "Material" way
=======================
This Zietgeist branch is targeted to SDK 21, Google’s second release of Lollipop.

Changes to get the app on 21:

* Changing setOutline to setOutlineProvider in the FabView class

* Changing from L to 21 in the build.gradle defaultConfig

* Changing the Palette constructor and tinting animations to be async in the GlobalDetailsActivity

* Changing setViewName to setTransitionName

* Changing the styles file to have windowAllowReturnTransitionOverlap instead of windowAllowExitTransitionOverlap

Applico's design and development team was looking at an opportunity to implement some of the philosophical changes that have come with the soon to be released Android "L" version of the Android OS and in turn
the new Google framework around "Material Design". This repo is an attempt to implement and refresh the current Google Zeigeist 2012 application that currently lives on the Google Play store:

https://play.google.com/store/apps/details?id=com.google.android.apps.zeitgeist&hl=en


###Goals
==============================
Under the artifacts folder there is a ppt presentation on our goals for the project and what we accomplished.

The code itself was an attempt to excercise some of the following areas in the
accompanying Android documentation found here:
https://developer.android.com/preview/material/index.html

Including:

* Implementation of a custom FabView that Applico could customize and reuse on all of its projects

* Shared element transitions between activites

* The RecyclerView

* The CardView

* Theming and use of the new primary and secondary colors

* Touch feedback, in particular the new ripple drawables

* Animated State List Drawables for the FabView

* Drawable Tinting and the Palette Class

###Future work

Some of the more advanced topics like curved motion and the reveal and path based implementations didn't make it into this demo.  But we encourage people to contribute and help us out!

* Reveal animations

* Path based animations

* Use new interpolators

* Multiple shared elements in activity transistions

###License

Copyright 2014 Applico, Inc.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.





