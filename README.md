OfflineBuilder
==============

[![Build Status](http://siika.fi:8888/jenkins/job/OfflineBuilder%20(Vaadin)/badge/icon)](http://siika.fi:8888/jenkins/job/OfflineBuilder%20(Vaadin)/)

Description
-----------

OfflineBuilder allows to define offline UI without need of writing GWT code. Add-on offers offline extended versions of 
basic Vaadin UI components and layouts. This way your server defined UI can be used on offline mode too.

Known Limitations
-----------------

Known limitations of current implementation:
*   Currently offline root and it's children must be also in online UI and defined as visible.


Supported Offline Components
----------------------------

Components that should work well in offline mode:
*   VerticalLayout and OfflineHorizontalLayout
*   CssLayout
*   FormLayout
*   Button (offline click handling still missing)
*   Label
*   Image
*   Link

Partially Supported Offline Components
--------------------------------------

Components with some issues:
*   FormLayout
*   CheckBox

Not Supported Components
------------------------

Some Vaadin components would be really hard to get working in offline mode. This is almost always caused by legacy
communication code of these components. I have to rewrite these components to get them working.
*   Panel (uses UIDL -> hard to implement, use CssLayout)
*   Fields (legacy components, rewritten component required)
*   Table (legacy component, maybe grid in 7.3)

Planned Features
----------------

Features planned to be added in future versions:
*   Hidden component hierarchy of offline components and their states on client side
*   Data input in offline mode. Reading API for that data when returning to online mode
*   Replacement field components required to data input

Current State
-------------

Add-on in current format is highly experimental. It is proof-of-concept of writing offline UIs with server side Vaadin
code. Each release may break compatibility with older versions.

License Notice
--------------

As Touchkit 4's open source license is AGPL, this add-on is also released with AGPL v3 license. If you want to use code
from this add-on in closed source projects (with TouchKit's CVAL v3.0 license), please contact me.

Contact Information
-------------------

If you have something to ask, please contact me with email sami(dot)viitanen(at)vaadin(dot)com
