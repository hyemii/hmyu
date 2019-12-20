package com.project.hmyu.posts;

import com.project.hmyu.type.Status;
import com.project.hmyu.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    PostRepository postRepository;

    // 글 저장
    @PostMapping
    public ResponseEntity<Post> insPost(@RequestBody Post post){
        postRepository.save(post);
        return ResponseEntity.ok(post);
    }

    // 글 수정
    @PutMapping("/{postId}")
    public ResponseEntity<Post> modPost(@PathVariable int postId, @RequestBody Post post){
        Post modPost = postRepository.findByPostId(postId);
        modPost.setSubject(post.getSubject());
        modPost.setContents(post.getContents());
        modPost.setStatus(post.getStatus());
        postRepository.save(modPost);
        return ResponseEntity.ok(modPost);
    }

    // 리스트
    @GetMapping
    public ResponseEntity<Page<Post>> listPost(@Valid String type, Pageable pageable){
        Page<Post> posts = null;
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), new Sort(Sort.Direction.DESC, "postId"));

        // 공개 글
        if(type.equals(Type.G.toString())){
            posts = postRepository.findByStatus(Status.O, pageRequest);
        }

        // 모든 글
        if(type.equals(Type.A.toString())) {
            posts = postRepository.findAll(pageRequest);
        }

        if(posts == null){ return ResponseEntity.notFound().build(); }
        return ResponseEntity.ok(posts);
    }

    // 글 읽기
    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPost(@PathVariable int postId){
        Post post = postRepository.findByPostId(postId);
        if(post == null){ return ResponseEntity.notFound().build(); }
        return ResponseEntity.ok(post);
    }

    // 글 삭제
    @DeleteMapping("/{postId}")
    public void delPost(@PathVariable int postId) {
        Post post = postRepository.findByPostId(postId);
        postRepository.delete(post);
    }
}
