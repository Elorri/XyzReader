# Xyzreader

## Add config description for each supported devices

XyzReader chosen config

|   /    |  w600dp<  |  '>= w600dp  | 
|:------:|:-------:|:-----------:|
|  Land  |  land    |     w600dp-land     |
|  Port  |  D    |     w600dp-D  |   

	
# description of layout for each config

	- layout
		- colored-toolbar-with-title				activity_article_list
		- empty-container-for-fragment				activity_article_detail
		- one-column-grid-layout-with-tile-item		fragment_list containing an include with the gridview
		- transparent-collapsing-toolbar-with-collapsed-title-top-dynamic-height-image-bottom-info-fab-bottom-right	fragment_article_detail
		- gridview-for-include
	- layout-land
		- colored-Ushape-toolbar-with-top-right-share-button-with-2/3-max-width-fragment activity_article_detail
		- with-transparent-collapsing-toolbar-with-collapsed-title-top-dynamic-height-image-bottom-info-white-bg	fragment_article_detail
		- gridview-for-include
	- layout-w600dp/refs.xml 
		- gridview-for-include
	- layout-w600dp-land/refs.xml
		- gridview-for-include


# Release 2 
The apps comform to material design standards. To achieve this we will go through several steps.

## building_a_total_experience_for_phone
	- [x] add a theme for the all app with branded colors
	- [x] add a nice font for toolbar title	
	- [x] add photography
	- [x] enforce image ratio
	- [ ] add subtle shadow or gradient for better text visibility : drawable / photo_background_protection
	- [ ] checkout padding and margins MD standards / make every item easily scanable
	- [x] use roboto everywhere with different styling
	- [ ] checkout font size
	- [x] add iconography animated icon	
	- [x] add drawable statelist on images?
	- [x] add a floating action button with ripple and rising effect
	- [x] add a style to every elements that will share the same style or you want to reuse in future apps.
	- [ ] name the differentes surfaces and their elevation
	- [ ] check that tools is used correctly in each layouts
	- [ ] check if icons are correct dimension
	- [x] assets should be in main
	- [ ] changer l'icone
	- [ ] add instructive motion for viewpager
	- [x] instructive motion. genre de drawer
	- [x] see if you find for what is used mDrawInsetsFrameLayout and use it again
	- [ ] verif que l'�l�vation fonctionne sur tablette


## add_animations
	- [x] add a seam to step transition
	- [x] add a parallax effect - photocontainer article detail fragment
	- [ ] add content transition
	- [ ] add shared element transition	
	- [ ] add instructive motion for viewpager	


## add_tablet_support
	- [ ] make a toolbar title appearing one step bellow if enough space


## 2.2_accessibility
  - [] Add content description
 

# Detail Statistics

| Branches  | LOC    | Hours |
| ------------- | :-----------: |:-------------: |
| 2.0_errors_and_guidance_messages|851   |  29   |
| 2.1_building_a_total_experience|13871  |34     |
| 2.2_accessibility|38|2|
| 2.3_localisation|22|8|
| 2.4_performance|-205|8|




#General Statistics


| LOC     | Hours   | 
| :------:| :-----: |
|  14557  |   43    | 

Starting Code number of LOC : 5179