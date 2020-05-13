package miu.edu.cs545.utility;


import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import miu.edu.cs545.domain.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class BlobAzure {
    private  static  String connectStr = "DefaultEndpointsProtocol=https;AccountName=oojavascript;AccountKey=HmZYcEpkG5HJccOJ1d0Zp8m9CGQuh4J2Euylf26h23sGQ5+m27naxP4ul6XlrpaKq/tu78Ng/TkM/CI1OrG9uA==;EndpointSuffix=core.windows.net";
    public  String Upload (Product product){

        String path="";
        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(connectStr).buildClient();

        String containerName = "waaimage" ;

        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);

        MultipartFile image = product.getImage();

        String fileName = java.util.UUID.randomUUID() +"_"+ image.getOriginalFilename();

        InputStream is = null;
        try {
            String contentType = image.getContentType();
            if (contentType != null) {
                is = new DataInputStream(image.getInputStream());
                long length = image.getSize();
                BlobClient blob = containerClient.getBlobClient(fileName);
                blob.upload(is, length);
            }
            path= "https://oojavascript.blob.core.windows.net/waaimage/"+fileName;

        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {

        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException ignored) {
                // Log the Exception

            }
        }
        return path;
    }

    public static String Upload (byte[] file, String format){

        String path="";
        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(connectStr).buildClient();

        String containerName = "waaimage" ;

        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);

        String fileName = java.util.UUID.randomUUID()+ "."+format;

        InputStream stream = null;
        try {

               stream = new ByteArrayInputStream(file);
                BlobClient blob = containerClient.getBlobClient(fileName);
                blob.upload(stream, file.length);

            path= "https://oojavascript.blob.core.windows.net/waaimage/"+fileName;

        } finally {
            try {
                if (stream != null) {
                    stream.close();
                }
            } catch (IOException ignored) {
                // Log the Exception

            }
        }
        return path;
    }
}
