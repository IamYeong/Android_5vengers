# 오벤져스   

## 소개   

<img src="https://user-images.githubusercontent.com/59534301/116390005-94162600-a858-11eb-8619-53aef9473f98.png" width="80px" height="80px" title="px(픽셀) 크기 설정" alt="RubberDuck"></img>

- 저를 올바르게 이끌어준 친구 4명이 있고,   
저를 포함해서 총 5명입니다.   
앱개발을 해서 뭔가 도움이 될 수 있지 않을까 생각하다가   
친구들끼리만 사용하는 SNS가 있다면 좋을 것 같아서 기획했습니다.

1. 카카오 로그인을 합니다.
3. 유저정보가 Firestore 에 저장됩니다.
4. 이미 있다면 갱신, 없다면 추가입니다.
5. 로그인 된 유저의 정보는 프로필이나 게시글 작성 등에 요긴하게 쓰이므로   
Singleton 으로 작성하여 정보를 잃지 않도록 했습니다.
6. 게시글을 Post type으로 불러와서 RecyclerView에 반영합니다.
7. FloatingActionButton 을 터치하면 게시글 작성 화면(WriteActivity)으로 이동합니다.
8. 제목과 내용을 쓰고 확인 버튼을 누르면 Firestore에 저장하고 메인목록을 갱신합니다.
9. 목록 요소를 선택하면 제목/작성시간/작성자/내용 이 나오는 PostActivity로 Intent합니다.
10. 댓글보기를 누르면 Fragment가 transaction 되면서 댓글목록을 Firestore 에서 가져옵니다.
11. 가져온 댓글목록을 Comment type 으로 리스트에 담아서 RecyclerView 에 표기합니다.
12. 댓글을 작성하면 마찬가지로 Comment type에 담아서 Firestore에 저장하고 목록을 갱신합니다.
* Post 도 Comment 도, 생성자가 두 개입니다. 새롭게 만들어질 때는 현재의 Date 를 생성해서 저장하고,   
Firestore에서 가져올 때는 이미 작성된 시간을 time 필드에 넣기 때문입니다.


***


## 스크린샷

**ID를 활용하여 실시간으로 이름을 가져오고 싶은데,   
onComplete() 메서드 콜백 타이밍에서 애를 먹고 있습니다.   
여기서 지연되고 있으니 조금 기다려주세요**   

<!--

![종료]()
<img src="" width="100px" height="" title="px(픽셀) 크기 설정" alt="RubberDuck"></img>

로그인, 프로필, 메인 리스트, 글쓰기 화면, 게시글 확인, 댓글작성, 댓글확인, 파이어스토어 캡쳐
-->



***

## 분석

- KakaoAPI : Kakao에서 제공하는 API를 활용하여 로그인과 유저정보활용에 사용했습니다.

- DrawableLayout : 기본으로 제공하는 레이아웃을 사용하고 개인적으로 코드를 분석해봤던게 기억에 남습니다.

- Glide : 이미지를 다루는 것은 Glide 하나만 있어도 되는게 아닌가 싶을 정도로 너무 편했습니다.   
Kakao 로그인을 하면서 가져온 Image URL 을 Glide 를 통해서 프로필에 set 하고,   
네모난 이미지를 Glide 를 사용하여 동그랗게 만들었습니다   

- Firestore : NoSQL 형식인 Firestore 에 유저정보와 게시글 정보를 저장하고   
커스텀 객체를 만들어서 다뤄봤습니다.   
(Post 객체와 UserModel 객체입니다)   

- RefreshLayout : 아래로 당기면 목록이 갱신되는 기능을 onRefresh() 콜백함수를 사용해서 구현했습니다.   
동작 하나만 넣는 거라서 Interface를 구현하는 것 보다 간편한 방식인 익명객체를 선택헀습니다.   

- RecyclerView : 메인화면의 제목/작성자 로 구성된 게시글 목록을 받습니다.
