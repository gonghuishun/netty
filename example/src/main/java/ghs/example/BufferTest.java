package ghs.example;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.util.Arrays;

/**
 * @author: gonghs2
 * @date: 2020/3/27 14:27
 * @description:
 */
public class BufferTest {
    public static void main(String[] args) {
//        ByteBuf buf = Unpooled.buffer();//初始容量大小 256
//        System.out.println(buf.readerIndex()+"---"+buf.writerIndex()+"---"+buf.capacity());

//        ByteBuf buf1 = Unpooled.buffer(5);//初始容量大小 5
        ByteBuf buf1 = Unpooled.directBuffer(5);//初始容量大小 5
        System.out.println(buf1.readerIndex()+"---"+buf1.writerIndex()+"---"+buf1.capacity());
        byte[] bytes = {1,2,3};
        buf1.writeBytes(bytes);
        //directBuff 未实现array()方法
        System.out.println("初始容量5，写入{1,2,3}===>"+Arrays.toString(buf1.array()));
        System.out.println("初始地址===>"+buf1.toString());

        //读取字节
        byte b1 = buf1.readByte();
        byte b2 = buf1.readByte();
        System.out.println("读取字节："+Arrays.toString(new byte[] {b1,b2}));
        System.out.println("读取后===>"+Arrays.toString(buf1.array()));

        //丢弃读取过的字节  未读取的字节(writerIndex-readerIndex)从INDEX(0)开始覆盖原有数据  改变readerIndex 和writerIndex 的值
        buf1.discardReadBytes();
        System.out.println("丢弃读取字节===>"+Arrays.toString(buf1.array()));
        System.out.println(buf1.readerIndex()+"---"+buf1.writerIndex()+"---"+buf1.capacity());

        // 再次写入
        buf1.writeBytes(new byte[]{4});
        System.out.println("写入{4}===>"+Arrays.toString(buf1.array()));

        //将ByteBuf清零  readerIndex 和writerIndex 的值不变
        buf1.setZero(0,buf1.capacity());
        System.out.println("清零===>"+Arrays.toString(buf1.array()));
        System.out.println(buf1.readerIndex()+"---"+buf1.writerIndex()+"---"+buf1.capacity());

        //清空读写指针
        buf1.clear();
        System.out.println("清空读写指针===>"+Arrays.toString(buf1.array()));
        System.out.println(buf1.readerIndex()+"---"+buf1.writerIndex()+"---"+buf1.capacity());

        //写入一段超过容量的内容  触发扩容  未超过4M 从64开始 每次<<1  只至满足最小容量
        buf1.writeBytes(new byte[]{1,2,3,4,5,6,7,8});
        System.out.println("写入一段超过容量的内容===>"+Arrays.toString(buf1.array()));
        System.out.println(buf1.readerIndex()+"---"+buf1.writerIndex()+"---"+buf1.capacity());
        System.out.println("Bytebuf地址===>"+buf1.toString());
    }
}
