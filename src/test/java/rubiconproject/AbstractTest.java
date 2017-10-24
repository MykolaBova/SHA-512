package rubiconproject;

import rubiconproject.hash.HashGenerator;
import rubiconproject.hash.impl.Sha512HashGenerator;
import rubiconproject.service.FileHashService;
import rubiconproject.service.FileService;
import rubiconproject.service.impl.FileHashServiceImpl;
import rubiconproject.service.impl.FileServiceImpl;

public class AbstractTest {
    protected static final String FILE_A_HASH = "af371785c4fecf30acdd648a7d4d649901eeb67536206a9f517768f0851c0a06616f724b2a194e7bc0a762636c55fc34e0fcaf32f1e852682b2b07a9d7b7a9f9";
    protected static final String FILE_B_HASH = "46868d0a185e942d2fd15739b60096feab4ccdc99139cca4c9db82325606115c8803a6bffe37d6e54c791330add6e1fc861bfa79399f01cc88eed3fcedce13d4";
    protected static final String FILE_C_HASH = "c1e42aa0c8908c9c3d49879a4fc04a59a755735418ddc3a200e911673da188bf46f67818972eac54b38422895391c82b2b0e0cf34aea9468c3ad73c2d0ffa912";
    protected static final String FILE_D_HASH = "9dd88c920d86ac24112eb692e87b047bb6e69cd413593b009af62a29a71daa68f094dd3340976ae9b8e5d8e5d66d964179409c049103f91f3ccba80d9de63b7a";
    protected static final String FILE_E_HASH = "40c9964826072dbebe00ea99db34a8c8268088738de8d2a9c02743d0eed36a018adf122bacd789cc569ba2f5f54c75191683e3f252486bf71a5824ae99e20017";

    protected static final String FILE_A = "input/bar/fileA.dat";
    protected static final String FILE_B = "input/bar/fileB.dat";
    protected static final String FILE_C = "input/bar/fileC.dat";
    protected static final String FILE_D = "input/faz/fileD.dat";
    protected static final String FILE_E = "input/faz/fileE.dat";

    protected static final String FILE_A_CONTENT = "Nullam ornare, magna ac tincidunt congue, diam nisl vulputate velit, sed auctor nibh ex quis nisi. Duis suscipit purus a elementum sollicitudin. Morbi non eros vitae diam ornare auctor quis ac ipsum. Vestibulum et odio vitae mauris luctus imperdiet at quis nulla. Proin sem sapien, vestibulum sit amet vehicula vitae, sodales nec lacus. Praesent eget aliquet sem. Fusce malesuada semper luctus. Integer hendrerit neque erat, vitae dignissim justo vestibulum vel. Morbi eu purus a elit vulputate congue. Vivamus congue mattis quam et eleifend. Maecenas hendrerit elit non rutrum cursus. Vestibulum sodales arcu quis nisl ultricies maximus. Quisque turpis eros, pellentesque et nisl sit amet, elementum mollis eros. In ut massa sed lorem volutpat egestas sed elementum ex.\n";

    protected static final String DIR_INPUT = "input";
    protected static final String DIR_INPUT_HASH = "6dd415b8f89a52dd3ce277946150f1df6ea98a89296d0574db69b1fbc4d0aade51abba041529309abfbf07897808edb31a4a6b73a9b7c79fce20476062f6288a";

    protected static final HashGenerator GENERATOR = new Sha512HashGenerator();
    protected static final FileService FILE_SERVICE = new FileServiceImpl();
    protected static final FileHashService FILE_HASH_SERVICE = new FileHashServiceImpl(GENERATOR, FILE_SERVICE);

    protected ClassLoader classLoader = getClass().getClassLoader();
}
