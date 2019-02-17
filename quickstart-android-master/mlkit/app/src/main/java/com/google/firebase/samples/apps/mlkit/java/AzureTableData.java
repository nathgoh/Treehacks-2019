package com.google.firebase.samples.apps.mlkit.java;
import com.google.firebase.samples.apps.mlkit.java.FoodInfo;
import com.microsoft.azure.storage.*;
import com.microsoft.azure.storage.table.*;

public class AzureTableData {
    public static final String storageConnectionString =
            "DefaultEndpointsProtocol=https;" +
                    "AccountName=foodapp;" +
                    "AccountKey=vMivJ//N2DdfgpMD75EfX2UKlsAOmYi7ZSLVQxbsuQuAtiVAO08W0kZ4FFLxOeqRILVXrOOt4xWKFjTgq1F1iA==;";
    private CloudTable cloudTable;
    public AzureTableData() {
        try
        {
            // Retrieve storage account from connection-string.
            CloudStorageAccount storageAccount =
                    CloudStorageAccount.parse(storageConnectionString);
            // Create the table client.
            CloudTableClient tableClient = storageAccount.createCloudTableClient();
            // Create the table if it doesn't exist.
            String tableName = "foods";
            cloudTable = tableClient.getTableReference(tableName);
            // cloudTable.createIfNotExists();
        }
        catch (Exception e)
        {
            // Output the stack trace.
            e.printStackTrace();
        }
    }
    public CloudTable getTable() {
        return cloudTable;
    }
    public FoodInfo getEntity(String key) {
        System.out.println(key);
        try {
            TableOperation retrieve = TableOperation.retrieve(key, key, FoodInfo.class);
            System.out.println(cloudTable.execute(retrieve).getResult());
            FoodInfo specificFoodInfo = cloudTable.execute(retrieve).getResultAsType();
            if (specificFoodInfo != null) {
                System.out.println(specificFoodInfo.getCalories());
            }
            return specificFoodInfo;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}


