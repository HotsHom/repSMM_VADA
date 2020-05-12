from rest_framework import serializers
from .models import VkToken, Post, UserPost

class TokenSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = VkToken
        fields = ('id', 'user_id', 'token')

class PostSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = Post
        fields = ('id', 'user_id', 'title', 'text', 'likes')

class UserPostSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = UserPost
        fields = ('id', 'user_id', 'title', 'text', 'vk', 'date_post', 'group_id')

# class socialNetworksSerializer(serializers.ModelSerializer):
#     class Meta:
#         model = User
#         fields = ('id', 'username', 'first_name', 'last_name',)
