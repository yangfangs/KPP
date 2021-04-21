import kmer_contig.GetFeature;

import java.io.IOException;

import static utils.checkPath.checkInputFile;
import static utils.checkPath.checkOutputFile;

public class Feature {
    public static boolean run(String[] args) throws IOException, IOException {
        int argIdx = 0;
        String spePath = null;
        String outPath = null;
        String profile = null;
        int k = 1;
        int w =0;
        String model ="AA";

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
            else if (arg.equals("-w"))
                w = Integer.parseInt(args[argIdx++]);
            else if (arg.equals("-p"))
                profile = args[argIdx++];
            else {
                System.err.println("Unknown option: " + arg);
            }
        }
        spePath = checkInputFile(spePath);
        outPath = checkOutputFile(outPath);
        GetFeature foo = new GetFeature(spePath,
                profile,
                outPath,
                k,
                model);
        foo.createContig();
        foo.eachContig(w);
        return true;
    }
}
