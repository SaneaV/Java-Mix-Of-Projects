namespace cpp org.example
namespace java org.example
namespace py org.example

exception EntityNotFoundException {
    1: i32 code,
    2: string description
}

struct CrossPlatformResource {
    1: i32 id,
    2: string name,
    3: optional string salutation
}

service CrossPlatformService {

    CrossPlatformResource get(1:i32 id) throws (1:EntityNotFoundException e);

    void save(1:CrossPlatformResource resource);

    list <CrossPlatformResource> getList();

    string toString(1:CrossPlatformResource resource);
}