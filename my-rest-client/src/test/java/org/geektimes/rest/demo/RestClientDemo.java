package org.geektimes.rest.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.geektimes.rest.client.DefaultClient;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class RestClientDemo {

    public static void main(String[] args) throws JsonProcessingException {
        executeGet();
        executeForm();
        executeJson();
    }

    /**
     * get测试
     */
    public static void executeGet() {
        Client client = ClientBuilder.newClient();
        Response response = client
                .target("http://127.0.0.1:8080/hello/world")      // WebTarget
                .request() // Invocation.Builder
                .get();                                     //  Response

        String content = response.readEntity(String.class);

        System.out.println(content);
    }



    /**
     * 表单测试
     *     @RequestMapping("/hello/form")
     *     public User form( User user) {
     *         return user;
     *     }
     */
    public static void executeForm() {
        DefaultClient client = (DefaultClient) ClientBuilder.newClient();
        Form form = new Form();
        form.param("name", "张三");
        form.param("age", "21");
        Entity<Form> entity = Entity.form(form);

        Response response = client
                .target("http://127.0.0.1:8080/hello/form")      // WebTarget
                .request() // Invocation.Builder
                .post(entity);                                     //  Response

        User responseUser = response.readEntity(User.class);

        System.out.println(responseUser);
    }

    /**
     * json测试
     *     @RequestMapping("/hello/json")
     *     public User json(@RequestBody User user) {
     *         return user;
     *     }
     * @throws JsonProcessingException
     */
    public static void executeJson() throws JsonProcessingException {
        DefaultClient client = (DefaultClient) ClientBuilder.newClient();
        User user = new User();
        user.setName("张飞");
        user.setAge(30);
        ObjectMapper objectMapper = new ObjectMapper();
        String value = objectMapper.writeValueAsString(user);
        Entity<String> entity = Entity.json(value);

        Response response = client
                .target("http://127.0.0.1:8080/hello/json")      // WebTarget
                .request()// Invocation.Builder
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .post(entity);                                     //  Response

        User responseUser = response.readEntity(User.class);

        System.out.println(responseUser);
    }



    static class User{

        private String name;

        private Integer age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}

