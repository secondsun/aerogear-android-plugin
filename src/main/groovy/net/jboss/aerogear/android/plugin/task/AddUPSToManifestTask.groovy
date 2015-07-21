package net.jboss.aerogear.android.plugin.task

import com.android.build.gradle.AppPlugin
import com.android.build.gradle.api.AndroidSourceFile
import com.android.build.gradle.internal.dependency.ManifestDependencyImpl
import com.android.build.gradle.tasks.ProcessManifest
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.Nested
import org.gradle.api.tasks.TaskAction

class AddUPSToManifestTask extends DefaultTask {


    @Input
    AndroidSourceFile manifest

    @TaskAction
    protected void run() {

        def manifestXml = new XmlSlurper().parse(manifest.srcFile);

        if(!checkPermissions(manifestXml)) {
            addPermissions();
        }

        if (!checkReceiver(manifestXml)) {
            addReceiver();
        }

        if (!checkService(manifestXml)) {
            addService();
        }

    }

    /**
     * Add the Broadcast receiver to the Android manifest for UPS to run.
     */
    def addReceiver() {

    }

    /**
     * Check that the manifest includes the correct breakdcastReceiver to run UPS
     */
    def checkReceiver(manifest) {
        def receivers = manifest.application.'*'.find { node ->
            node.name() == 'receiver' && node['@android:name'] == 'org.jboss.aerogear.android.unifiedpush.gcm.AeroGearGCMMessageReceiver' && node['@android:permission'] == com.google.android.c2dm.permission.SEND
        }
        if (receivers == null || receivers.size() != 0) {
            return false;
        }

        def receiver = receivers[0];




        return true;
    }

    /**
     * Add the IDlistenerservice to the manifest
     */
    def addService() {

    }

    /**
     * Check that the manifest includes IDListenerService
     */
    def checkService(manifest) {
        return true;
    }

    /**
     * Add the necessary permissions to the Android Manifest for UPS to run.
     */
    def addPermissions() {

    }

    /**
     * Check that the manifest includes the correct permissions for using UPS.
     */
    def checkPermissions(manifest) {
        return true;
    }
}
