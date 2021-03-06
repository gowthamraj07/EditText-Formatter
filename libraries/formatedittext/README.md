[ ![Download](https://api.bintray.com/packages/gowthamraj07/EditText-Formatter/formatedittext/images/download.svg) ](https://bintray.com/gowthamraj07/EditText-Formatter/formatedittext/_latestVersion)

# Format Edit Text
This library helps to format the input (in EditText) while the user types. 
Currently this library supports any formats without dash (-) operator in output, 
as it uses dash (-) operator to specify the format. 

<a href='https://bintray.com/gowthamraj07/EditText-Formatter/formatedittext?source=watch' alt='Get automatic notifications about new "formatedittext" versions'><img src='https://www.bintray.com/docs/images/bintray_badge_color.png'></a>

Note :  
Due to a bug in emulator [*Check this answer*](https://stackoverflow.com/a/9146166/1679946),
If no input type is mentioned (example: as below), then characters might be duplicated

    android:inputType="number"


## How to use

### 1. Add library dependency
Add the following dependency to app/build.gradle or module/build.gradle file.

    implementation 'com.androidwidgets:formatedittext:0.2.0'

### 2. Declare FormatEditText in Layout (xml) file
    <FormatEditText
            android:id="@+id/ed_text"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:inputType="number" />

### 3. Set the format to the FormatEditText in Code
- If you need only format (when no validation required)

        final FormatEditText editText = findViewById(R.id.ed_text);
        editText.setFormat("<any format>");
        
- If you need validation
        
        final FormatEditText editText = findViewById(R.id.ed_text);
        editText.setValidator(new Validator(), new ValidationListener());
        editText.setFormat("<any format>");
        
## Validator and ValidationListener
Implement the following interface to define your own Validator and ValidationListener.

##### To define Validator implement
    FormatTextWatcher.Validator
    
##### To define ValidationListener implement
    FormatTextWatcher.ValidationListener

Validator.validate() method receives 2 inputs as parameters, Formatted and Unformatted inputs respectively,
to easy the validation steps.
Based on the validate response (true/false), methods (showSuccess()/showError()) from ValidationListener will get called.



## Examples
### Credit Card formats
| Credit Card Type | Format              |
|------------------|---------------------|
| Visa             | ---- ---- ---- ---- |
| Visa Electron    | ---- ---- ---- ---- |
| American Express | ---- ------ -----   |
| MasterCard       | ---- ---- ---- ---- |
| Maestro          | ---- ---- -----     |
|                  | ---- ------ -----   |
|                  | ---- ---- ---- ---- |


### Date formats
| Date Examples | Format            |
|---------------|-------------------|
| 22/Feb/2019   | --/---/----       |
| 22/Feb/19     | --/---/--         |
| 22-Feb-2019   | Not supported yet |
| 22/02/2019    | --/--/----        |
| 22/02/19      | --/--/--          |


### Mobile number formats ([Reference here](https://en.wikipedia.org/wiki/National_conventions_for_writing_telephone_numbers))
| Country             | Digits         | Mobile number Example | Format          |
|---------------------|----------------|-----------------------|-----------------|
| India               | 10 digits long | +91 AAAAA BBBBB       | +91 ----- ----- |
| Hong Kong and Macau | 8 digits long  | XXXX YYYY             | ---- ----       |
| Japan               |                | (0AA) NXX XXXX        | (---) --- ----  |


### Time formats
| Time example | Format      |
|--------------|-------------|
| 10:50:45 am  | --:--:-- -- |


### Screenshots

![](images/Screenshot_1.png?raw=true)

![](images/Screenshot_2.png?raw=true)

![](images/Screenshot_3.png?raw=true)


# License

Copyright 2020 Gowtham Shanmugaraj Ganesan

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
