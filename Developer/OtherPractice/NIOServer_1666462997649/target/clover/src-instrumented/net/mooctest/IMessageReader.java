/* $$ This file has been instrumented by Clover 4.4.1#2019101123313948 $$ */package net.mooctest;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

/**
 * Created by jjenkov on 16-10-2015.
 */
public interface IMessageReader {

    public void init(MessageBuffer readMessageBuffer);

    public void read(Socket socket, ByteBuffer byteBuffer) throws IOException;

    public List<Message> getMessages();



}
