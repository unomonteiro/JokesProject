project name: jokes
project id: jokes-207013


## References:

### [Build-It-Bigger Study Jam- June 18](https://docs.google.com/document/d/1UjODbCCybeUCrmN0gl6-HebPbOfgOpRYRh67gbNVrUI/edit)

### [Setting up local GCE Server - sharing my notes](https://discussions.udacity.com/t/setting-up-local-gce-server-sharing-my-notes/641123)  

## Problems found
### Issue: Execution failed for task ':backend:appengineStart'
>appengineStart  
'Execution failed for task ':backend:appengineStart'.  
\> Validation Error: Java App Engine components not installed. Fix by running 'gcloud components install app-engine-java' on command-line.'

**Solution**  
https://github.com/GoogleCloudPlatform/appengine-plugins-core/issues/416#issuecomment-365603096

Specify appengine install path in `backend/build.gradle`
```
appengine {  // App Engine tasks configuration
	tools.cloudSdkHome="gcloud-sdk-path"
}
```
find `gcloud-sdk-path` by running  
>`$(gcloud --format='value(installation.sdk_root)' info)`  

in the terminal.

Documentation here: https://cloud.google.com/appengine/docs/flexible/java/gradle-reference#global_properties

https://github.com/googleads/googleads-mobile-android-examples/tree/master/java/admob/InterstitialExample
