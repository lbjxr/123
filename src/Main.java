import java.io.File;
import java.util.Map;

public class Main {
//8 9 14
    public static void main(String[] args) throws Exception {
        String director = "C:\\Users\\LBJ\\Desktop\\0341276";
        File fileDirector = new File(director);
        File[] fileList = fileDirector.listFiles();
        String fileName = null;
        String fileStoreName = null;
        String fileOriName = null;
        String mapFileSize = null;

        //读取Excel表格
        ReadExcelUtils readExcelUtils = new ReadExcelUtils(director+"\\test.xls");
        Map<Integer, Map<Integer,Object>> map = readExcelUtils.readExcelContent();


        if(fileDirector.exists() && fileDirector.isDirectory()){
                //还原后文件和数据库文件信息比较
                for (int j = 1; j <= map.size(); j++) {

                    //数据库显示的原始文件名
                    fileOriName = map.get(j).get(1).toString();
                    //数据库保存的文件名
                    fileStoreName = map.get(j).get(2).toString();
                    //数据库保存的文件大小
                    mapFileSize = map.get(j).get(10).toString();
                    mapFileSize = mapFileSize.substring(0,mapFileSize.lastIndexOf("."));

                    for (int i = 0; i < fileList.length; i++){
                        fileName = fileList[i].getName();
                        //获取文件大小
                        long fileSize = fileList[i].length();
                        //获取文件类型
                        String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);


                    //先比较文件类型
                    if (fileOriName.substring(fileOriName.lastIndexOf(".") + 1).equals(fileType)){
                        //文件类型相同，比较文件大小
                        if (mapFileSize.equals(String.valueOf(fileSize))){
                            //将比较过的文件名置空，防止重复比较
                            map.get(j).put(1,"");
                            //打印结果
                            System.out.println("==================文件的大小和类型相同====================");
                            System.out.println("相同的文件为：（还原）" + fileName + "<<<>>> （数据库）" + fileOriName);
                            //打印还原后的文件信息
                            System.out.print("还原文件" + fileName + " 的文件大小：" + fileList[i].length());
                            System.out.println(" ;文件类型：" + fileName.substring(fileName.lastIndexOf(".") + 1));

                            //打印数据库文件信息
                            System.out.print("数据库中文件大小:" + mapFileSize);
                            System.out.println("  ;文件类型: " + fileOriName.substring(fileOriName.lastIndexOf(".") + 1));

                            //重命名文件保存
                            File saveDir = new File(director + "\\" + fileStoreName);
                            // fileList[i].createNewFile();
                            System.out.println("i==" + i + "===" + fileStoreName);
                            fileList[i].renameTo(saveDir);
                            break;
                        }
                    }else {
//                        System.out.println("文件大小和类型不同");
//                        System.out.println("不同的文件为：" + fileName + "<<<>>>" + fileOriName);
                    }
                }


//                if(fileList[i].getName().equals("项目源码")){break;}
//                String FileName = fileList[i].getName();
//                int SubIndex = FileName.indexOf("更多");
//                String SubName = FileName.substring(0,SubIndex-1);
//                System.out.printf("SubName="+SubName+"\n");
//                File SaveDir = new File(director + "\\new\\" + fileStoreName);
//                fileList[i].renameTo(SaveDir);
//                System.out.println(SaveDir.getName());
            }
        }
    }
}
