# AeroGear Android Plugin

This is a Gradle plugin for Android applications running AeroGear software.  It can perform simple checks to make sure
your code is properly integrated with AeroGear.

# Support

We support Gradle 2.1+

# Usage

```groovy
apply plugin: 'aerogear-android-plugin'
```

# Push Integration

```groovy

aerogear-unified-push {
    strictMode true
}

```

 * strictMode : Boolean : Will fail the build if push is not setup correctly otherwise will log a warning.  This will override autofix.
 * autofix : Boolean : will attempt to fix the manifest if an error is detected.