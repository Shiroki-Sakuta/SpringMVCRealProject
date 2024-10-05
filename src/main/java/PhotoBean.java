



import java.io.IOException;
import java.util.Base64;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
@Data
public class PhotoBean {
	private int id;
	private String name;
	private MultipartFile photo;
	private byte[] photoBytes;
	
	public String getPhotoBase64() throws IOException {
        if (this.photoBytes != null && this.photoBytes.length > 0) {
            return Base64.getEncoder().encodeToString(this.photoBytes);
        }
        return null;
    }
}
