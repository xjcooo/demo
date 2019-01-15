package org.xjc.demo.file;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.*;

public class ReadDemo {

    static String sql = null;
    static DBHelper db1 = null;
    static ResultSet ret = null;

    /**
     * @param args
     * @throws Exception
     * @Title: main
     * @Description: TODO
     * @return: void
     */
    public static void main(String[] args) throws Exception {
		ReadBlod2Pictures();
//        insertImage();
    }

    private void Test() {
        sql = "select JJR,YXXX from YXRY_APP.JJRZP_20190114";//SQL语句
        db1 = new DBHelper(sql);//创建DBHelper对象
        try {
            ret = db1.pst.executeQuery();//执行语句，得到结果集
            while (ret.next()) {
                String uid = ret.getString(1);
                String ufname = ret.getString(2);

                System.out.println(uid + "\t" + ufname + "\t");
                //System.out.println(uid + "\t" + ufname + "\t" + ulname + "\t" + udate );
            }//显示数据
            ret.close();
            db1.close();//关闭连接
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertImage() throws Exception {
        String path = "C:\\Users\\wangchh\\Desktop\\187620.jpg";
        FileInputStream in = new FileInputStream(new File(path));
        db1 = new DBHelper();
        Connection con = db1.getConn();
        PreparedStatement ps = con.prepareStatement("insert into YXRY_APP.JJRZP_20190114(jjr, yxxx) values(?,?)");
        ps.setInt(1, 1000000);
        ps.setBinaryStream(2, in, in.available());
        ps.executeUpdate();
        db1.close();
    }

    private static void ReadBlod2Pictures() throws Exception {

        byte[] data = null;
        //读取图片出来，保存到本地的磁盘上面
        sql = "select JJR,YXXX from YXRY_APP.JJRZP_20190114 where jjr=1000000";
        db1 = new DBHelper(sql);//创建DBHelper对象
        ret = db1.pst.executeQuery();//执行语句，得到结果集
        while (ret.next()) {
            Blob blob = ret.getBlob("YXXX");
            String s1 = "F:\\temp\\" + ret.getString(1) + ".jpg";
            InputStream in = blob.getBinaryStream();

            File file2 = new File(s1);
            OutputStream outputStream = new FileOutputStream(file2);
            data = blob.getBytes(1, (int) blob.length());


            int n = 0;// 每次读取的字节长度
            byte[] bb = new byte[1024];// 存储每次读取的内容
            while ((n = in.read(bb)) != -1) {
                outputStream.write(bb, 0, n);// 将读取的内容，写入到输出流当中
            }
            //执行完以上后，磁盘下的该文件才完整，大小是实际大小
            outputStream.close();

            System.out.println(s1);
        }
    }

    static void ByteToFile(String fileName, byte[] bytes) throws Exception {
        System.out.println(fileName);
        System.out.println(bytes.length);
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        BufferedImage bi1 = ImageIO.read(bais);
        try {
            File w2 = new File(fileName);//可以是jpg,png,gif格式   
            ImageIO.write(bi1, "png", w2);//不管输出什么格式图片，此处不需改动   
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            bais.close();
        }
    }

}
