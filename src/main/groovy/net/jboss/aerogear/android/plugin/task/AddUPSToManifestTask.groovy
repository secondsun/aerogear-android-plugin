package net.jboss.aerogear.android.plugin.task

import com.android.build.gradle.internal.dependency.ManifestDependencyImpl
import com.android.build.gradle.tasks.ProcessManifest
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.Nested
import org.gradle.api.tasks.TaskAction

class AddUPSToManifestTask extends ProcessManifest {


    protected void doFullTaskAction() {
        if(!checkPermissions()) {
            addPermissions();
        }

        if (!checkReceiver()) {
            addReceiver();
        }

        if (!checkService()) {
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
    def checkReceiver() {
        return false;
    }

    /**
     * Add the IDlistenerservice to the manifest
     */
    def addService() {

    }

    /**
     * Check that the manifest includes IDListenerService
     */
    def checkService() {
        return false;
    }

    /**
     * Add the necessary permissions to the Android Manifest for UPS to run.
     */
    def addPermissions() {

    }

    /**
     * Check that the manifest includes the correct permissions for using UPS.
     */
    def checkPermissions() {
        return false;
    }
}
