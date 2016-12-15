package com.lapots.functional.sandbox

import org.apache.commons.io.*;
import org.apache.commons.compress.compressors.xz.XZCompressorInputStream;

int buffersize = 1000

FileInputStream fileStream = new FileInputStream("play\\ghc-8.0.1-x86_64-unknown-mingw32.tar.xz")
BufferedInputStream input = new BufferedInputStream(fileStream)
FileOutputStream out = new FileOutputStream("play\\ghc-8.0.1-x86_64-unknown-mingw32.tar")
XZCompressorInputStream xzIn = new XZCompressorInputStream(input)

final byte[] buffer = new byte[buffersize];
int n = 0;
while (-1 != (n = xzIn.read(buffer))) {
    out.write(buffer, 0, n);
}
out.close();
xzIn.close();
