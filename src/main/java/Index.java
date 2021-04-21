import kmer_contig.CreateContigIndex;

import java.io.IOException;

import static utils.checkPath.*;

public class Index {
    public static boolean run(String[] args) throws IOException {
        int argIdx = 0;
        String spePath = null;
        String outPath = null;
        int k = 1;
        int splitNum=1;
        int max=1;
        String model = "AA";

        while (argIdx < args.length && args[argIdx].startsWith("-")) {
            String arg = args[argIdx++];
            if (arg.equals("-i"))
                spePath = args[argIdx++];
            else if (arg.equals("-o"))
                outPath = args[argIdx++];
            else if (arg.equals("-d"))
                model = args[argIdx++];
            else if (arg.equals("-k"))
                k = Integer.parseInt(args[argIdx++]);
            else if (arg.equals("-m"))
                max = Integer.parseInt(args[argIdx++]);
            else if (arg.equals("-s"))
                splitNum = Integer.parseInt(args[argIdx++]);
            else {
                System.err.println("Unknown option: " + arg);
            }
        }
        spePath = checkInputFile(spePath);
        outPath = checkOutputFileIndex(outPath);

        CreateContigIndex foo = new CreateContigIndex(k,
                spePath,
                outPath,
                splitNum,
                max,
                model);
        foo.create();
        return true;
    }
}
