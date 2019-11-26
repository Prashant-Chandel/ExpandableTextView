# ExpandableTextView
This is an Android library for dealing with Readmore option in text view at the last of the text.
# DEMO Screen shots



![alt text](https://github.com/Prashant-Chandel/ExpandableTextView/blob/master/ScreenShot/Screenshot_1574769509.png)
![alt text](https://github.com/Prashant-Chandel/ExpandableTextView/blob/master/ScreenShot/Screenshot_1574769513.png)
![alt text](https://github.com/Prashant-Chandel/ExpandableTextView/blob/master/ScreenShot/Screenshot_1574769517.png)
![alt text](https://github.com/Prashant-Chandel/ExpandableTextView/blob/master/ScreenShot/Screenshot_1574769629.png)
![alt text](https://github.com/Prashant-Chandel/ExpandableTextView/blob/master/ScreenShot/Screenshot_1574769632.png)



# Dependency

Add the dependencies to your Gradle files:

-Step 1. Add it in your root build.gradle at the end of repositories
## Gradle
```java
   allprojects {
       repositories {
     ...
     maven { url 'https://jitpack.io' }
     }
    }
```

-Step 2. Add the dependency
```java
   dependencies {
	        implementation 'com.github.Prashant-Chandel:ExpandableTextView:1.0.1'
	}
```
Step 1. Add the JitPack repository to your build file
## maven
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
Step 2. Add the dependency

	<dependency>
	    <groupId>com.github.Prashant-Chandel</groupId>
	    <artifactId>ExpandableTextView</artifactId>
	    <version>1.0.1</version>
	</dependency>

# Usage
```java
  AddReadMoreOption readMoreOption = new AddReadMoreOption.Builder(this).build();

  // OR using options to customize

 AddReadMoreOption readMoreOption = new AddReadMoreOption.Builder(context)
              // Optional parameters
                .setTextLength(3, AddReadMoreOption.TYPE_LINE) //OR
              //.setTextLength(300, AddReadMoreOption.TYPE_CHARACTER)
                .setMoreLabel("MORE")
                .setLessLabel("LESS")
                .setMoreLabelColor(Color.RED)
                .setLessLabelColor(Color.BLUE)
                .setLabelUnderLine(true)
                .setExpandAnimation(true)
                .build();

  readMoreOption.addReadMoreTo(textView, getString(R.string.long_desc));
```

# Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update the tests as appropriate.

# License

### Copyright 2018 Prashant Chandel

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

