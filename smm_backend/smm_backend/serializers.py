from rest_framework import serializers
from .models import VkToken, Post

class TokenSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = VkToken
        fields = ('id', 'user_id', 'token')

class PostSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = Post
        fields = ('id', 'user_id', 'title', 'text', 'likes')

# class socialNetworksSerializer(serializers.ModelSerializer):
#     class Meta:
#         model = User
#         fields = ('id', 'username', 'first_name', 'last_name',)
