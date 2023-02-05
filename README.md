[![Release](https://jitpack.io/v/lasnikprogram/fabric-login.svg)](https://jitpack.io/#lasnikprogram/fabric-login)
# Minecraft switched to Microsoft accounts as a mean of logging in. Therefore the code is outdated
# Fabric Login
Allows your mod to log into minecraft accounts. Really easy and really fast. 
</br>
It is just a tool for developers. Not for users.
</br>
</br>
## How to add fabric-login to your development enviroment:
### 1. Add [JitPack](https://github.com/jitpack/jitpack.io) to your `build.gradle`:
```gradle
repositories {
  maven { url "https://jitpack.io" }
}
```
### 2. Add fabric-login to your `build.gradle`:
```gradle
dependencies {
  modImplementation "com.github.lasnikprogram:fabric-login:FABRIC-LOGIN_VERSION"
}
```
❗ `FABRIC-LOGIN_VERSION` should get replaced by the version you want to use. 
</br> 
You find them under [tags](https://github.com/lasnikprogram/fabric-login/tags) (the latest one is also in green at the beginning of this `Readme`). Alternatively you can use the hash of any commit.

## Usage
```java
import fabric.login.Login;

new Login (username);
new Login (email, password);
new Login (String email, String password, Proxy proxy, String clientToken, Agent agent, String accountType);
```
Read the Javadoc for more information.
