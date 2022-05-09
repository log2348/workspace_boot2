
preparedStatement 사용 방법

Statement와 preparedStatement의 차이점 - 캐시의 사용 유무

사용자 ---> 바나나 ---> (램) ---> 하드디스크(서치)

사용자 --> 바나나 (캐시되었다)

따라서 반복적으로 쿼리를 수행한다면 Statement에 비해 성능이 훨씬 좋다.
그리고 보안적인 측면에서 Statement 보다 preparedStatement가 안정성이 높다. (권장사항)

