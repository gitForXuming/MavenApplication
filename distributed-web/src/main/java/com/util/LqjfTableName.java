package com.util;

import java.io.*;

/**
 * Created by lenovo on 2019/4/28.
 * Title LqjfTableName
 * Package  com.util
 * Description
 *
 * @Version V1.0
 */
public class LqjfTableName {

    private static String dataDir ="E:\\资料\\兴业托管\\网银\\网银迁移\\临朐聚丰\\sdlq20190423\\sdlq";
    private static String GXDZB = "E:\\资料\\兴业托管\\网银\\网银迁移\\临朐聚丰\\表名中英文对应关系.txt";
    public static void main(String[] args) {

        InputStreamReader reader = null;
        BufferedReader br = null;

        OutputStreamWriter write =null;
        BufferedWriter bw = null;

        //手机号维护
        try{
            File pbPayee = new File(dataDir+File.separator+"pmb"+ File.separator+"移动网银信息表.del");
            File pbPayeeNew = new File(dataDir+File.separator+"pmb"+ File.separator+"updateMobile.sql");
            reader = new InputStreamReader(new FileInputStream(pbPayee),"UTF-8");
            br = new BufferedReader(reader);
            write =new OutputStreamWriter(new FileOutputStream(pbPayeeNew ,true));
            bw =new BufferedWriter(write);
            String line = br.readLine();
            int count =0;
            while(null != line && "" != line){
                String[] tmp = line.split(",");
                StringBuffer update = new StringBuffer();
                String moblieNo = tmp[8];
                if(null!=moblieNo&&(moblieNo = moblieNo.replace(" ","").replace("\"","")).length()==11){
                    update.append("update pb_cstinf_net t set  pcn_mobile='").append(moblieNo).append("'");
                    update.append(" where pcn_cstno='").append(tmp[1]).append("';\n");
                    count++;
                    if(count%100==0){
                        update.append("commit;\n");
                    }
                    bw.write(update.toString());
                }

                bw.flush();
                line = br.readLine();
            }
            bw.write("commit;\n");

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try{
                bw.close();
                write.close();
                br.close();
                reader.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        if(true){
            return;
        }
        //个人收款人源文件处理
        try{
            File pbPayee = new File(dataDir+File.separator+"per"+ File.separator+"个人收款人信息表.del");
            File pbPayeeNew = new File(dataDir+File.separator+"per"+ File.separator+"个人收款人信息表new.del");
            reader = new InputStreamReader(new FileInputStream(pbPayee),"UTF-8");
            br = new BufferedReader(reader);
            write =new OutputStreamWriter(new FileOutputStream(pbPayeeNew ,true));
            bw =new BufferedWriter(write);

            String line = br.readLine();
            while(null != line && "" != line){
                String[] tmp = line.split(",");
                StringBuffer tmpLine = new StringBuffer("").append(tmp[0]).append(",");
                tmpLine.append(tmp[1]).append(",");
                tmpLine.append(tmp[14]).append(",");
                tmpLine.append(tmp[2]).append(",");
                tmpLine.append(tmp[3]).append(",");
                tmpLine.append(tmp[4]).append(",");
                tmpLine.append(tmp[5]).append(",");
                tmpLine.append(tmp[6]).append(",");
                tmpLine.append(tmp[7]).append(",");
                tmpLine.append(tmp[8]).append(",");
                tmpLine.append(tmp[9]).append(",");
                tmpLine.append(tmp[10]).append(",");
                tmpLine.append(tmp[11]).append(",");
                tmpLine.append(tmp[12]).append(",");
                tmpLine.append(tmp[13]).append(",").append("\n");
                bw.write(tmpLine.toString());
                bw.flush();
                line = br.readLine();
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try{
                bw.close();
                write.close();
                br.close();
                reader.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        //企业收款人源文件处理
        try{
            File cbPayee = new File(dataDir+File.separator+"ent"+ File.separator+"企业收款人信息表.del");
            File cbPayeeNew = new File(dataDir+File.separator+"ent"+ File.separator+"企业收款人信息表new.del");
            reader = new InputStreamReader(new FileInputStream(cbPayee),"UTF-8");
            br = new BufferedReader(reader);
            write =new OutputStreamWriter(new FileOutputStream(cbPayeeNew ,true));
            bw =new BufferedWriter(write);

            String line = br.readLine();
            while(null != line && "" != line){
                String[] tmp = line.split(",");
                StringBuffer tmpLine = new StringBuffer("").append(tmp[0]).append(",");
                tmpLine.append(tmp[1]).append(",");
                tmpLine.append(tmp[14]).append(",");
                tmpLine.append(tmp[2]).append(",");
                tmpLine.append(tmp[3]).append(",");
                tmpLine.append(tmp[4]).append(",");
                tmpLine.append(tmp[5]).append(",");
                tmpLine.append(tmp[6]).append(",");
                tmpLine.append(tmp[7]).append(",");
                tmpLine.append(tmp[8]).append(",");
                tmpLine.append(tmp[9]).append(",");
                tmpLine.append(tmp[10]).append(",");
                tmpLine.append(tmp[11]).append(",");
                tmpLine.append(tmp[12]).append(",");
                tmpLine.append(tmp[13]).append(",").append("\n");
                bw.write(tmpLine.toString());
                bw.flush();
                line = br.readLine();
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try{
                bw.close();
                write.close();
                br.close();
                reader.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        // 文件名转换
        try{
            File file = new File(GXDZB);
            reader = new InputStreamReader(new FileInputStream(file),"UTF-8");
            br = new BufferedReader(reader);
            String line = br.readLine();
            while(null != line && "" != line){
                String[] tmp = line.split("\\|");
                String cName = tmp[0];
                String eName = tmp[1];
                String folderPath = tmp[2];
                File delFile = new File(dataDir+File.separator+folderPath+File.separator+cName+".del");
                if(delFile.exists()){
                    delFile.renameTo(new File(dataDir+File.separator+folderPath+File.separator+eName+".del"));
                    System.out.println(cName+"------"+eName+"-------"+"del");
                }


                File outFile = new File(dataDir+File.separator+folderPath+File.separator+cName+".out");
                if(outFile.exists()){
                    outFile.renameTo(new File(dataDir+File.separator+folderPath+File.separator+eName+".out"));
                    System.out.println(cName+"------"+eName+"-------"+"out");
                }
                line = br.readLine();
                System.out.println(line);
            }

        }catch(Exception e){
            e.printStackTrace();;
        }finally {
            try{
                br.close();
                reader.close();;
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
}
