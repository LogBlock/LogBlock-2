package org.logblock;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import org.yaml.snakeyaml.Yaml;

public class Configuration
{

    public static Configuration load(File file) throws IOException
    {
        if (!file.exists())
        {
            file.createNewFile();
        }

        Yaml yaml = new Yaml();

        Reader in = new FileReader(file);
        Configuration conf = yaml.loadAs(in, Configuration.class);
        if (conf == null)
        {
            conf = new Configuration();
        }

        String dump = yaml.dumpAsMap(conf);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(dump);
        writer.close();

        return conf;
    }
}
