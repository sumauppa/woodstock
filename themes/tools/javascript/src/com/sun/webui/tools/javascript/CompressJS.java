package com.sun.webui.tools.javascript;

import java.io.*;
import com.sun.webui.tools.ExecProcess;

public class CompressJS {
    private String rhinoJar = null;
    private boolean verbose = false;

    /**
     * Constructor.
     *
     * @param rhinoJar The jar containing the Rhino compress tool.
     * @param verbose Enable verbose output.
     */
    public CompressJS(String rhinoJar, boolean verbose) {
        this.rhinoJar = rhinoJar;
        this.verbose = verbose;
    }

    /**
     * Compress JavaScript directory or file.
     *
     * @param sourcePath Path to JavaScript directory or file.
     */
    public void compress(String sourcePath) throws IOException {
        iterate(sourcePath);
    }

    /**
     * Compress JavaScript file.
     *
     * Note: Calling org.mozilla.javascript.tools.shell.Main.main
     * directly does not work well with large file sets. After only a few files, 
     * Java runs out of memory when redirecting stdout to a file. And when the
     * -o option is used in this scenario, Rhino compresses the same files. For
     * example:
     * 
     * Compressed file stored in 'scheduler.js'
     * Compressed file stored in 'image.js'
     * Compressed file stored in 'image.js'
     * Compressed file stored in 'textArea.js'
     * Compressed file stored in 'textArea.js'
     * Compressed file stored in 'textArea.js'
     * ...
     * 
     * Therefore, we need to run the Rhino program in a separate JVM and 
     * compress individual files.
     *
     * @param file The JavaScript file to compress.
     */
    private void compressFile(File file) throws IOException {
        // The command line equivalent of the exec command is:
        //
	// java -jar <rhinoJar> -strict -opt -1 -o <outputfile>
	// 	-c <filetocompress> 
	//
	String cmd = 
            "java -jar " +  rhinoJar + 
	    " -strict -opt -1 -o " +
            file.getAbsolutePath() +
            " -c " +
            file.getAbsolutePath();
	ExecProcess ep = new ExecProcess(cmd);
	int returnVal = ep.exec();
	if (returnVal != 0) {
	    System.exit(returnVal);
	}
    }

    /**
     * Iterate over JavaScript directory or file.
     *
     * @param sourcePath Path to JavaScript directory or file.
     */
    private void iterate(String sourcePath) throws IOException {
        File file = new File(sourcePath);
        if (file.isDirectory()) {
            String[] fileNames = file.list();
            for (int i = 0; i < fileNames.length; i++) {
                String fileName = file.getAbsolutePath() + 
                    file.separator + fileNames[i];
                iterate(fileName);
            }
        } else {
            compressFile(file);
        }
    }
}
