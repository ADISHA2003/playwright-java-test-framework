package Pojos.entityMongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Data
@Document(collection = "financial_docs")
public class FinancialDocMongo {
    @Id
    private String _id;
    private String id;
    private int company_id;
    private int financial_year;
    private String file_name;
    private String file_path;
    private List<String> entities;
    private List<FileContent> file_content;
    private String filetxt;
    private int document_type;

    // Getters and Setters
}
