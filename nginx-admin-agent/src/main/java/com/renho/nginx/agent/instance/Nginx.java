package com.renho.nginx.agent.instance;

import com.renho.nginx.agent.model.NginxEnv;
import org.zeroturnaround.exec.ProcessExecutor;
import org.zeroturnaround.exec.ProcessResult;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author: renho
 * @since: 1.0.0
 */
public enum Nginx {
    /**
     *
     */
    INSTANCE;

    public boolean isEnvValid(NginxEnv nginxEnv) {
        return true;

    }

    public boolean check(NginxEnv nginxEnv) {
        Future<ProcessResult> future = null;
        try {
            future = new ProcessExecutor()
                    .command(nginxEnv.getNginxBin(), "-t", "-c", nginxEnv.getNginxConfFile())
                    .readOutput(true)
                    .start().getFuture();
            String output = future.get(60, TimeUnit.SECONDS).outputUTF8();
            System.out.println(output);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return false;
    }

}
