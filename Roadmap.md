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

