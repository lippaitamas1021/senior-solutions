//package activities;
//
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.PagingAndSortingRepository;
//import org.springframework.data.repository.query.Param;
//
//import java.util.List;
//
//public interface ActivityRepository extends PagingAndSortingRepository<Activity, Long>, CustomizedActivityRepository {
//
//    List<Activity> findByNameIgnoreCase(String name);
//
//    @Query("select a from Activity a where length(a.name) = :nameLength")
//    List<Activity> findByNameLength(@Param("nameLength") int nameLength);
//}