package com.dbdou.arts.rpc.common.codec;

import com.dbdou.arts.rpc.common.util.SerializationUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class DouEncoder extends MessageToByteEncoder {

    private Class<?> genericClass;

    public DouEncoder(Class<?> genericClass) {
        this.genericClass = genericClass;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Object in, ByteBuf buf) throws Exception {
        if (genericClass.isInstance(in)) {
            byte[] data = SerializationUtil.serialize(in);
            buf.writeInt(data.length);
            buf.writeBytes(data);
        }
    }
}
