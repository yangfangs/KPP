package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParserSeq {
    private String [] description;
    private String [] sequence;

    public ParserSeq(String filename)
    {
        readSequenceFromFile(filename);
    }

    private void readSequenceFromFile(String file)
    {
        List desc= new ArrayList();
        List seq = new ArrayList();
        try{
            BufferedReader in     = new BufferedReader( new FileReader( file ) );
            StringBuffer   buffer = new StringBuffer();
            String         line   = in.readLine();

            if( line == null )
                throw new IOException( file + " is an empty file" );

            if( line.charAt( 0 ) != '>' )
                throw new IOException( "First line of " + file + " should start with '>'" );
            else
                desc.add(line);
            for( line = in.readLine().trim(); line != null; line = in.readLine() )
            {
                if( line.length()>0 && line.charAt( 0 ) == '>' )
                {
                    seq.add(buffer.toString());
                    buffer = new StringBuffer();
                    desc.add(line);
                } else
                    buffer.append( line.trim() );
            }
            if( buffer.length() != 0 )
                seq.add(buffer.toString());
        }catch(IOException e)
        {
            System.out.println("Error when reading "+file);
            e.printStackTrace();
        }

        description = new String[desc.size()];
        sequence = new String[seq.size()];
        for (int i=0; i< seq.size(); i++)
        {
            description[i]=(String) desc.get(i);
            sequence[i]=(String) seq.get(i);
        }

    }

    //return sequence as a String
    public String getSequence(int i){ return sequence[i];}

    //return description as String
    public String getDescription(int i){return description[i];}

    public int size(){return sequence.length;}
}
